package br.com.leo.wppstats.data.dao

import br.com.leo.wppstats.data.model.ObStatus
import br.com.leo.wppstats.data.model.ObUsuario

class StatusDao {

    companion object {
        private var id = 0
        get() {
            field++
            return field
        }

        private val statusNovos = arrayListOf(
            ObStatus(id, true, false, "https://api.adorable.io/avatars/285/$id.png", "", ObUsuario("usuario$id")),
            ObStatus(id, true, false, "", "Exemplo com texto curto", ObUsuario("usuario$id")),
            ObStatus(id, true, false, "https://api.adorable.io/avatars/285/23.png", "", ObUsuario("usuario$id")),
            ObStatus(id, true, false, "https://api.adorable.io/avatars/285/597.png", "", ObUsuario("usuario$id")),
            ObStatus(id, true, false, "", "Exemplo de texto longo para mostrar na tela de visualização", ObUsuario("usuario$id")),
            ObStatus(id, true, false, "https://api.adorable.io/avatars/285/06651.png", "", ObUsuario("usuario$id"))
        )

        private val statusLidos = ArrayList<ObStatus>()
    }

    fun buscaStatusNovos(): ArrayList<ObStatus> {
        statusNovos.sortBy { obStatus -> obStatus.id }
        return statusNovos
    }

    fun buscaStatusLidos(): ArrayList<ObStatus> {
        statusLidos.sortBy { obStatus -> obStatus.id }
        return statusLidos
    }

    fun marcarComoVisto(obStatus: ObStatus) {
        val iterator = statusNovos.iterator()

        while (iterator.hasNext()) {
            val st = iterator.next()
            if (st.id == obStatus.id) {
                st.ehNovo = false
                statusLidos.add(st)
                iterator.remove()
                return
            }
        }
    }

    fun criaStatusImagem(caminho: String) {
        statusNovos.add(ObStatus(StatusDao.id, true, false, caminho, "", ObUsuario.meuUsuario()))
    }

    fun criaStatusTexto(texto: String) {
        statusNovos.add(ObStatus(StatusDao.id, true, false, "", texto, ObUsuario.meuUsuario()))
    }
}