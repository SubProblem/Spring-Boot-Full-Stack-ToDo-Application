async function handleRegister() {
  event.preventDefault();

  const firstname = document.getElementById("firstname").value;
  const lastname = document.getElementById("lastname").value;
  const email = document.getElementById("email").value;
  const password = document.getElementById("password").value;
  const confirmPassword = document.getElementById("confirmPassword").value;

  if (password !== confirmPassword) {
    alert("Passwords do not match");
    return;
  }

  const data = {
    firstname: firstname,
    lastname: lastname,
    email: email,
    password: password
  };

  try {
    const response = await fetch("http://localhost:8080/api/v1/auth/register", {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify(data)
    });

    if (response.ok) {
        alert("User registered")
    }
  } catch (error) {
    console.error("Error:", error);
  }
}

document.getElementById("register-btn").addEventListener("click", handleRegister);


document.addEventListener('DOMContentLoaded', () => {
  const container = document.querySelector('.container');
  const windowHeight = window.innerHeight;
  const containerHeight = container.offsetHeight;

  const translateY = (windowHeight - containerHeight) / 2;

  container.style.transform = `translate(-50%, ${translateY}px)`;

  setTimeout(() => {
    container.style.transform = 'translate(-50%, -50%)';
  }, 300); 
});