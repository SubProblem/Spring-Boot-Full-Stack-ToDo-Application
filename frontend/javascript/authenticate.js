

async function handleLogin() {
  event.preventDefault();

  const email = document.getElementById("email").value;
  const password = document.getElementById("password").value;

  const data = {
    email: email,
    password: password
  };

  try {
    const response = await fetch("http://localhost:8080/api/v1/auth/authenticate", {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify(data)
    });

    if (response.ok) {
      const data = await response.json();
      const token = data.token;
      console.log(token);
      localStorage.setItem("jwtToken", token);
      window.location.href = "todo-list.html";
    } else {
      console.log("Login failed");
    }
  } catch (error) {
    console.error("Error:", error);
  }
}

document.getElementById("login-btn").addEventListener("click", handleLogin);


document.addEventListener('DOMContentLoaded', () => {
  const container = document.querySelector('.container');
  const windowHeight = window.innerHeight;
  const containerHeight = container.offsetHeight;

  const translateY = (windowHeight - containerHeight) / 2;

  container.style.transform = `translate(-50%, -${translateY}px)`;

  setTimeout(() => {
    container.style.transform = 'translate(-50%, -50%)';
  }, 200); 
});