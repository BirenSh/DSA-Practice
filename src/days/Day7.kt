package days

fun main(){
    val building = arrayOf(
        intArrayOf(0, 1, 0,1),
        intArrayOf(1, 1, 1,8),
        intArrayOf(0, 1, 0,8)
    )

    val board: Array<CharArray> = arrayOf(
        charArrayOf('.', '.', '4', '.', '.', '.', '6', '3', '.'),
        charArrayOf('.', '.', '.', '.', '.', '.', '.', '.', '.'),
        charArrayOf('5', '.', '.', '.', '.', '.', '.', '9', '.'),
        charArrayOf('.', '.', '.', '5', '6', '.', '.', '.', '.'),
        charArrayOf('4', '.', '3', '.', '.', '.', '.', '.', '1'),
        charArrayOf('.', '.', '.', '7', '.', '.', '.', '.', '.'),
        charArrayOf('.', '.', '.', '5', '.', '.', '.', '.', '.'),
        charArrayOf('.', '.', '.', '.', '.', '.', '.', '.', '.'),
        charArrayOf('.', '.', '.', '.', '.', '.', '.', '.', '.')
    )

   val result  =  isValidSudoku(board)

    println(result)
}


fun landCount(board: Array<IntArray>):Int{
    var count = 0
    for (r in board.indices){
        for (c in board.indices){
            if (board[r][c]==1) count++
        }
    }

    return  count

}
fun countRow(board: Array<CharArray>): Boolean {
    val row0 = 0
    val seen = HashSet<Char>()
    for (c in board[row0].indices){
        val currentNumber = board[row0][c]
        if (currentNumber != '.'){
            if (seen.contains(currentNumber)) return false //found duplicate
        }
        seen.add(currentNumber)
    }
    println(seen)


    return true
}

fun countColumn(board: Array<CharArray>): Boolean{

    val seen = HashSet<Char>()
    for (row in board[3].indices){
        val currentNum = board[row][3]
        if (currentNum != '.'){
           if(seen.contains(currentNum)) return false
        }
        seen.add(currentNum)
    }
    return true
}


fun isValidSudoku(board: Array<CharArray>): Boolean {
    val rowSeen = Array(9){ HashSet<Char>() }
    val colSeen = Array(9){ HashSet<Char>() }
    val boxes = Array(9) { HashSet<Char>() }

    for (row in board.indices){
        for (col in board.indices){
            val char = board[row][col]

            if (char == '.') continue
            //check row
            if (!rowSeen[row].add(char)) return false

            if (!colSeen[col].add(char)) return false

            val boxIndex = (row / 3) * 3 + (col / 3)
            if (boxes[boxIndex].add(char)) return false



        }
    }

    return true
}