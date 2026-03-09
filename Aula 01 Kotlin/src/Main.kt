
fun main() {
    val carros = listOf("Cerato", "Corolla", "Golf", "Fusca", "Opala")


    val listaDeRegras = listOf(
        Requisito("Mínimo de 5 caracteres") { it.length >= 5 },

        Requisito("Deve conter um número menor que 5") {it.any{numero -> numero.isDigit() && numero.digitToInt() < 5}},

        Requisito("Deve conter uma letra maiúscula") {it.any{letra -> letra.isUpperCase()}},

        Requisito("Deve conter um carro") {carros.any{carro -> it.contains(carro)}},

        Requisito("Deve conter um caracter especial") {it.any{caracter
            -> !caracter.isDigit() && !caracter.isLetter() && !caracter.isWhitespace()}},

        Requisito("Deve conter 17 caracteres ou menos") {it.length <= 17}
    )

    var senhaAprovada = false

    do {
        println("\nDigite sua senha:")
        val entrada = readLine() ?: ""

        var erroEncontrado: String? = null

        for (regra in listaDeRegras) {
            if (!regra.validacao(entrada)) {
                erroEncontrado = regra.mensagemErro
                break
            }
        }
        if (erroEncontrado != null) {
            println("ERRO: $erroEncontrado")
        } else {
            println("SUCESSO! Senha aceite pelo Overlord.")
            senhaAprovada = true
        }
    } while (!senhaAprovada)
}

data class Requisito(
    val mensagemErro: String,
    val validacao: (String) -> Boolean
)
