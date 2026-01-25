

fun main(){
    val str = "()))"
   println( isValidBrute(str))
}

fun romanToInt(s: String): Int {
    var prev = 0
    var sum = 0
    val tere =HashMap<Char, Int>()
    tere.getOrDefault('s',0)

    for (i in s.length - 1 downTo 0) {
        val current = when (s[i]) {
            'I' -> 1
            'V' -> 5
            'X' -> 10
            'L' -> 50
            'C' -> 100
            'D' -> 500
            'M' -> 1000
            else -> 0
        }

        if (current < prev) {
            sum -= current
        } else {
            sum += current
        }

        prev = current
    }
    return sum
}

fun isValidBrute(s: String): Boolean {
   val stack = mutableListOf<Char>()
    if (s.length %2 !=0 )return false
    if (!s.contains('(') && !s.contains('{') && !s.contains('[')) return false
    for (char in s){
         when(char){
            '(' -> stack.add(')')
            '[' -> stack.add(']')
            '{' -> stack.add('}')
             else -> {
                 if (stack.isNotEmpty() && stack.removeLast() != char)
                     return false
             }
        }

    }
    println(stack)
    return stack.isEmpty()
}
