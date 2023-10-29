fun main() {
    println("Введите массив слов, разделяя их пробелом:")
    val input = readln()
    if (input.isNullOrBlank()) {
        println("Вы ввели пустую строку.")
        return
    }

    val words = input.split(" ")
    val wordGroups = groupWords(words)

    println("Группы слов с одинаковыми буквами:")
    for (group in wordGroups) {
        println(group.joinToString(", "))
    }
}

fun groupWords(words: List<String>): List<List<String>> {
    val group = mutableMapOf<String, MutableList<String>>()

    for (word in words) {
        val sortedWord = word.toCharArray().sorted().joinToString("")
        if (!group.containsKey(sortedWord)) {
            group[sortedWord] = mutableListOf()
        }
        group[sortedWord]?.add(word)
    }

    return group.values.toList()
}
