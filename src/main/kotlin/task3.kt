fun main() {
    val alphabet = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЬЫЪЭЮЯ"
    val alphabetIndices = listOf(21, 13, 4, 20, 22, 1, 25, 12, 24, 14, 2, 28, 9, 23, 3, 29, 6, 16, 15, 11, 26, 5, 30, 27, 8, 18, 10, 33, 31, 32, 19, 7, 17)

    println("Выберите действие:")
    println("1. Зашифровать текст")
    println("2. Расшифровать текст")
    val choice = readln().toIntOrNull()

    when (choice) {
        1 -> {
            println("Введите текст для шифровки:")
            val message = readln()
            println("Введите ключевое слово:")
            val keyword = readln()
            val encryptedMessage = encryptMessage(message, keyword, alphabet, alphabetIndices)
            println("Зашифрованный текст: $encryptedMessage")
        }
        2 -> {
            println("Введите текст для расшифровки:")
            val encryptedMessage = readln()
            println("Введите ключевое слово:")
            val keyword = readln()
            val decryptedMessage = decryptMessage(encryptedMessage, keyword, alphabet, alphabetIndices)
            println("Расшифрованный текст: $decryptedMessage")
        }
        else -> println("Некорректный выбор.")
    }
}

fun encryptMessage(message: String?, keyword: String?, alphabet: String, alphabetIndices: List<Int>): String {
    if (message == null || keyword == null) return ""

    val messageChars = message.uppercase().toCharArray()
    val keywordChars = keyword.uppercase().toCharArray()
    val encryptedChars = CharArray(messageChars.size) // Создание массива символов для зашифрованного текста

    for (i in messageChars.indices) {
        val messageChar = messageChars[i]
        val keywordChar = if (i < keywordChars.size) keywordChars[i] else keywordChars[i % keywordChars.size]

        // Получение кода символа сообщения и ключевого слова
        val valueNumMessageChar = alphabetIndices[alphabet.indexOf(messageChar)]
        val valueNumKeywordChar = alphabetIndices[alphabet.indexOf(keywordChar)]

        // Получение нового кода символа
        val alphabetLength = alphabet.length
        var newMessageNum = valueNumMessageChar + valueNumKeywordChar
        newMessageNum = if (newMessageNum < alphabetLength) newMessageNum else (newMessageNum) % alphabetLength

        // Получение нового индекса по значению кода и сохранение в массив символов для зашифрованного текста
        val newMessageCharIndex = alphabetIndices.indexOf(newMessageNum)
        encryptedChars[i] = alphabet[newMessageCharIndex]
    }

    return String(encryptedChars)
}

fun decryptMessage(encryptedMessage: String?, keyword: String?, alphabet: String, alphabetIndices: List<Int>): String {
    if (encryptedMessage == null || keyword == null) return ""

    val encryptedChars = encryptedMessage.uppercase().toCharArray()
    val keywordChars = keyword.uppercase().toCharArray()
    val decryptedChars = CharArray(encryptedChars.size)

    for (i in encryptedChars.indices) {
        val encryptedChar = encryptedChars[i]
        val keywordChar = if (i < keywordChars.size) keywordChars[i] else keywordChars[i % keywordChars.size]

        // Получение кода символа зашифрованного сообщения и ключевого слова
        val valueNumEncryptedChar = alphabetIndices[alphabet.indexOf(encryptedChar)]
        val valueNumKeywordChar = alphabetIndices[alphabet.indexOf(keywordChar)]

        // Вычисление исходного кода символа
        val alphabetLength = alphabet.length
        var originalMessageNum = valueNumEncryptedChar - valueNumKeywordChar
        originalMessageNum = if (originalMessageNum >= 0) originalMessageNum else originalMessageNum + alphabetLength

        // Получение исходного индекса по значению кода и сохранение в массив символов для расшифрованного текста
        val originalMessageCharIndex = alphabetIndices.indexOf(originalMessageNum)
        decryptedChars[i] = alphabet[originalMessageCharIndex]
    }

    return String(decryptedChars)
}
