package days
//26 Jan 2026
fun main(){
//    val strs: Array<String> = arrayOf("act","pots","tops","cat","stop","hat")
//    val result = groupAnagrams2(strs)
//    println(result?.joinToString())

    val str = "biren sharmahe"
    println(lengthOfLastWord2(str))

}

//group Anagram
/**
 * ⏱ Complexity (Interview Answer)
 *
 * Sorting each word: O(k log k)
 *
 * Total words n
 *
 * ✅ Time: O(n · k log k)
 * ✅ Space: O(n · k)
 */
fun groupAnagrams(strs: Array<String>): List<List<String>>? {
    val map = HashMap<String, MutableList<String>>()

    for (cStr in strs){
        val sorted = cStr.toCharArray().sorted().joinToString("")
//        if (map.containsKey(sorted)){
//            map[sorted]!!.add(cStr)
//        }else{
//           map[sorted] = mutableListOf(cStr)
//        }
        map.getOrPut(key = sorted){mutableListOf()}.add(cStr) // this one line can be replaceable of if else

    }
    val result =  map.values.toList()
    return result
}

/**
 *
 * n = number of strings
 *
 * k = average length of each string
 *
 * 1️Loop over all strings → O(n)
 *
 * 2️Count characters in each string → O(k)
 *
 * 3️joinToString over 26 elements → O(26) → constant
 *
 * 4️HashMap operations → O(1) average
 *
 * Total time complexity : O(n · k)
 *
 * Total Space complexity: O(n · k)
 */
fun groupAnagrams2(strs: Array<String>): List<List<String>>? {
    val map = HashMap<String, MutableList<String>>()

    for (i in strs){
        val alpha = IntArray(26){0}

        for (char in i){
            alpha[char - 'a']++
        }
        val key = alpha.joinToString("#")
        map.getOrPut(key){
            mutableListOf()
        }.add(i)

    }
    return map.values.toList()

}

/**
 * for (i in s.length-1 downTo 0)
 *
 * In worst case (string like " " or "abc "), it scans all characters
 *
 * Cost: O(n)
 *
 * second loop
 * for (i in lastIndex-1 downTo 0) -> O(n)
 *
 * val result = s.substring(firstIndex, lastIndex + 1) -> O(n)
 *
 * O(n) + O(n) + O(n) = O(n)
 *
 * SPACE
 * lastIndex, firstIndex -> O(1)
 *
 * val result = s.substring(...) -> Extra space: O(n)
 *
 * final Space : O(n)
 *
 *
 *
 */
//String | 58. Length of Last Word
fun lengthOfLastWord(s: String): Int {
    var lastIndex=0
    //first loop for last index
    for (i in s.length-1 downTo 0){
        if (s[i] != ' '){
            lastIndex =  i

            break
        }
    }


    var firstIndex = 0
    // second loop for starting index
    for (i in lastIndex downTo 0){
        if (s[i]== ' '){
            firstIndex = i+1
            break

        }
    }
    println("$firstIndex and $lastIndex")
    val result  = s.substring(firstIndex,lastIndex+1)

    return result.length
}


/**
 * Time: O(n) : No substring,
 *
 * Space: O(1) : No extra variables
 */
fun lengthOfLastWord2(s: String): Int {
    var size = 0  // stor store str size
    var i = s.length-1

    //this will lookup for char
    //loop start form last because i= str size
    while (i>=0 && s[i] == ' '){
        i--     //on found char reduce the index
    }

    // run while not getting space, this will lookup for space
    while (i>=0 && s[i] != ' '){    // if space found loop will stop
        size++  // keep increase the size
        i--     // reduce the index
    }

    return size
}