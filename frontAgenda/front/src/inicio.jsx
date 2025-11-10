import { useEffect, useState } from "react";

export default function App() {

  const [compromissos, setCompromissos] = useState([]);
  const [filtro, setFiltro] = useState("");

  // Campos do formulário
  const [idEdit, setIdEdit] = useState(null);
  const [titulo, setTitulo] = useState("");
  const [data, setData] = useState("");
  const [hora, setHora] = useState("");
  const [descricao, setDescricao] = useState("");

  // ============================
  // BUSCAR LISTA AO CARREGAR
  // ============================
  async function carregarCompromissos() {
    const response = await fetch("http://localhost:8085/compromissos");
    const lista = await response.json();
    setCompromissos(lista);
  }

  useEffect(() => {
    carregarCompromissos();
  }, []);

  // ============================
  // SALVAR (CRIAR OU EDITAR)
  // ============================
  async function salvar(e) {
    e.preventDefault();

    const compromisso = {
      titulo,
      dataHora: data + "T" + (hora || "00:00"),
      descricao
    };

    let url = "http://localhost:8085/compromissos";
    let method = "POST";

    if (idEdit) {
      url += "/" + idEdit;
      method = "PUT";
    }

    const response = await fetch(url, {
      method,
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(compromisso)
    });

    if (response.ok) {
      alert("Salvo com sucesso!");
      limparFormulario();
      carregarCompromissos();
    } else {
      alert("Erro ao salvar!");
    }
  }

  // ============================
  // EDITAR → preenche o formulário
  // ============================
  function editar(comp) {
    setIdEdit(comp.id);
    setTitulo(comp.titulo);

    const dt = comp.dataHora.split("T");
    setData(dt[0]);
    setHora(dt[1].substring(0, 5));

    setDescricao(comp.descricao || "");
  }

  // ============================
  // EXCLUIR
  // ============================
  async function excluir(id) {
    if (!confirm("Deseja apagar este compromisso?")) return;

    const response = await fetch("http://localhost:8085/compromissos/" + id, {
      method: "DELETE"
    });

    if (response.ok) {
      carregarCompromissos();
    }
  }

  // ============================
  // LIMPAR FORMULÁRIO
  // ============================
  function limparFormulario() {
    setIdEdit(null);
    setTitulo("");
    setData("");
    setHora("");
    setDescricao("");
  }

  // ============================
  // FILTRO
  // ============================
  const listaFiltrada = compromissos.filter((c) =>
    c.titulo.toLowerCase().includes(filtro.toLowerCase())
  );

  // ============================
  // EXPORTAR CSV
  // ============================
  function exportarCSV() {
    const linhas = compromissos.map(
      (c) => `${c.id};${c.titulo};${c.dataHora};${c.descricao || ""}`
    );

    const csv = "ID;Título;Data;Descrição\n" + linhas.join("\n");

    const blob = new Blob([csv], { type: "text/csv" });
    const url = URL.createObjectURL(blob);

    const a = document.createElement("a");
    a.href = url;
    a.download = "compromissos.csv";
    a.click();
  }

  return (
    <div className="app">
      <header>
        <h1>Agenda de Compromissos</h1>
      </header>

      <div className="layout">
        {/* FORMULÁRIO */}
        <section className="card">
          <h2>{idEdit ? "Editar compromisso" : "Novo compromisso"}</h2>

          <form onSubmit={salvar}>

            <div>
              <label>Título</label>
              <input type="text"
                value={titulo}
                onChange={(e) => setTitulo(e.target.value)}
                required
              />
            </div>

            <div className="row">
              <div style={{ flex: 1 }}>
                <label>Data</label>
                <input type="date"
                  value={data}
                  onChange={(e) => setData(e.target.value)}
                  required
                />
              </div>

              <div style={{ width: "120px" }}>
                <label>Hora</label>
                <input type="time"
                  value={hora}
                  onChange={(e) => setHora(e.target.value)}
                />
              </div>
            </div>

            <div>
              <label>Descrição</label>
              <textarea
                value={descricao}
                onChange={(e) => setDescricao(e.target.value)}
              />
            </div>

            <div className="actions">
              <button type="button" className="btn-ghost" onClick={limparFormulario}>
                Limpar
              </button>

              <button type="submit" className="btn-primary">
                {idEdit ? "Salvar" : "Adicionar"}
              </button>
            </div>
          </form>

          <hr />
          <div className="muted">
            {idEdit ? `Editando ID: ${idEdit}` : "Nenhum compromisso selecionado"}
          </div>
        </section>

        {/* LISTA */}
        <aside>
          <div className="card">

            <div className="list-header">
              <strong>Compromissos agendados</strong>
              <input
                className="search"
                placeholder="Pesquisar..."
                value={filtro}
                onChange={(e) => setFiltro(e.target.value)}
              />
            </div>

            <div className="comp-list">
              {listaFiltrada.map((c) => (
                <div key={c.id} className="item">
                  <strong>{c.titulo}</strong>
                  <div>{c.dataHora.replace("T", " ")}</div>

                  <div className="item-actions">
                    <button className="small btn-ghost" onClick={() => editar(c)}>
                      Editar
                    </button>
                    <button className="small btn-ghost" onClick={() => excluir(c.id)}>
                      Excluir
                    </button>
                  </div>
                </div>
              ))}
            </div>

            <div className="muted">Total: {listaFiltrada.length}</div>

            <div>
              <button className="small btn-ghost" onClick={exportarCSV}>
                Exportar CSV
              </button>
            </div>

          </div>
        </aside>
      </div>
    </div>
  );
}
