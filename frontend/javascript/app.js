const todoInput = document.getElementById("todo-input")
const addTaskButton = document.getElementById("add-task-btn")
const todoList = document.getElementById("todo-list")



window.onload = async () =>  {
    const jwtToken = localStorage.getItem("jwtToken")
    
    const response = await fetch("http://localhost:8080/api/v1/user", {
        method: "GET",
        headers: {
            "Content-Type": "application/json",
            "Authorization": `Bearer ${jwtToken}`
        }
    })

    if (!response.ok) {
        throw new Error("Failed to fetch todo list.")
    }

    const data = await response.json();
    console.log(data);

    const todoList = document.getElementById("todo-list")


    data.forEach(task => {
        const taskItem = createTaskItem(task.task)
        taskItem.id = task.id
        todoList.appendChild(taskItem)
    });
}



const addTask = async () => {
    const taskText = todoInput.value.trim()
    console.log(taskText);
    if (taskText !== '') {
        const taskItem = createTaskItem(taskText)
        todoList.appendChild(taskItem)
        todoInput.value = ''
        
    }
    if (taskText === '') {
        return
    }
    const data = {
        task: taskText
    }

    try {
        const jwtToken = localStorage.getItem("jwtToken")
        console.log(jwtToken);
        const response = await fetch("http://localhost:8080/api/v1/user", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                "Authorization": `Bearer ${jwtToken}`,
            },
            body: JSON.stringify(data)
        })

        if (!response.ok) {
            console.log("Faile to add task");
            return
        }

        const contentType = response.headers.get('content-type');
        if (contentType && contentType.includes('application/json')) {
            const responseData = await response.text();
            
            console.log("Task added successfully", responseData);
        } else {
            console.log("Unexpected response format");
        }
    } catch (error) {
        console.error("Error: ", error);
    }
}




const createTaskItem = (taskText) => {

    const taskItem = document.createElement("li")
    taskItem.className = "todo-item"

    const taskTextSpan = document.createElement('span')
    taskTextSpan.textContent = taskText

    const deleteBtn = document.createElement('button')
    deleteBtn.textContent = 'Delete'
    deleteBtn.classList.add('delete-btn')
    deleteBtn.addEventListener('click', deleteTask)

    taskItem.appendChild(taskTextSpan)
    taskItem.appendChild(deleteBtn)
    
    return taskItem

}

const deleteTask = async (event) => {
    const jwtToken = localStorage.getItem("jwtToken");
    console.log(jwtToken);
    const taskItem = event.target.parentNode
    todoList.removeChild(taskItem)
    console.log(taskItem.id);
    const id = taskItem.id

    const response = await fetch(`http://localhost:8080/api/v1/user/task/${id}`, {
        method: "DELETE",
        headers: {
            "Content-Type": "application/json",
            "Authorization": `Bearer ${jwtToken}`            
        }
    })
    console.log(response);
    if (!response.ok) {
        console.log("Failed to delete task");
        return
    }

}


const toggleTaks = (event) => {
    const taskItem = event.target.parentNode
    taskItem.classList.toggle('completed')
}

addTaskButton.addEventListener('click', addTask)

todoInput.addEventListener('keydown', function (event) {
    if (event.key == "Enter") {
        addTask()
    }
})









