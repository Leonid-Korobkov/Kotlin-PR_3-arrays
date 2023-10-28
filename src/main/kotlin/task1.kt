fun main() {
    print("Введите количество строк: ")
    val numRows = readln().toIntOrNull()

    print("Введите количество столбцов: ")
    val numCols = readln().toIntOrNull()

    if (numRows == null || numCols == null || numRows <= 0 || numCols <= 0) {
        println("Некорректный ввод. Пожалуйста, введите положительное число строк и столбцов.")
        return
    }

    val matrix = Array(numRows) { IntArray(numCols) }
    val digitsSet = mutableSetOf<Int>()

    println("Вводите трехзначные числа последовательно через \"Enter\"")
    for (i in 0..<numRows) {
        for (j in 0..<numCols) {
            while (true) {
                print("arr[$i][$j] = ")
                val currentNum = readln().toIntOrNull()

                if (currentNum != null && currentNum in 100..999) {
                    matrix[i][j] = currentNum
                    digitsSet.add(currentNum)
                    break  // Выход из цикла while, так как число корректное
                } else {
                    println("Вы ввели некорректное число в строке $i, столбце $j. Введите трехзначное число.")
                }
            }
        }
    }

    println("Двумерный массив:")
    for (i in 0..<numRows) {
        for (j in 0..<numCols) {
            print("${matrix[i][j]}\t")
        }
        println()
    }

    println("В массиве использовано ${digitsSet.size} различных числа")
}
