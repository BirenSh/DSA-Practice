package days

fun main(){
    val building = arrayOf(
        intArrayOf(0, 1, 0,1),
        intArrayOf(1, 1, 1,8),
        intArrayOf(0, 1, 0,8)
    )
   val result  =  isValidSudoku(building)

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
fun isValidSudoku(board: Array<IntArray>): Boolean {
    val row0 = 0
    val seen = HashSet<Int>()
    for (c in board[row0].indices){
        val currentNumber = board[row0][c]
        if (currentNumber != 0){
            if (seen.contains(currentNumber)) return false //found duplicate
        }
        seen.add(currentNumber)
    }
    println(seen)


    return true
}
