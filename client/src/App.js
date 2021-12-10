import { useEffect, useState } from "react"

const App = () => {
  const [clients, setClients] = useState()

  useEffect(() => {
    // const response = fetch("/clients")
    // const body = await response.json()
    // setClients(body)
    // fetch("localhost:8080/clients").then((response) => setClients(response))
    fetch("http://localhost:8080/clients")
  })

  console.log("CLIENTS", clients)

  return (
    <div>
      <h1>WHATS MOFO</h1>
    </div>
  )
}

export default App
