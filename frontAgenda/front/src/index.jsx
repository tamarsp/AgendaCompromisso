import { useState } from "react";
import { Link } from "react-router-dom";

export default function Login() {
  const [email, setEmail] = useState("");
  const [senha, setSenha] = useState("");

  async function handleLogin(e) {
    e.preventDefault();

    const response = await fetch("http://localhost:8085/api/auth/login", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ email, senha })
    });

    const data = await response.json();

    if (data.token) {
      localStorage.setItem("token", data.token);
      alert("Login realizado!");
    } else {
      alert("Email ou senha incorretos!");
    }
  }

  return (
    <>
      <header>
        <nav className="nav p-3 bg-light shadow-sm">
          <Link className="nav-link" to="/cadastro">Cadastro</Link>
          <Link className="nav-link" to="/login">Login</Link>
        </nav>
      </header>

      <main className="container mt-5">
        <div className="row justify-content-center">
          <div className="col-12 col-md-6">
            <div className="card p-4 shadow">
              <h2 className="text-center mb-4">Login</h2>

              <form onSubmit={handleLogin}>
                <div className="mb-3">
                  <label className="form-label">Email</label>
                  <input
                    type="email"
                    className="form-control"
                    value={email}
                    onChange={(e) => setEmail(e.target.value)}
                  />
                </div>

                <div className="mb-3">
                  <label className="form-label">Senha</label>
                  <input
                    type="password"
                    className="form-control"
                    value={senha}
                    onChange={(e) => setSenha(e.target.value)}
                  />
                </div>

                <button className="btn btn-primary w-100" type="submit">
                  Entrar
                </button>
              </form>

              <p className="text-center mt-3">
                Não tem conta?{" "}
                <Link to="/cadastro" className="fw-bold">
                  Cadastre-se
                </Link>
              </p>
            </div>
          </div>
        </div>
      </main>
    </>
  );
}
