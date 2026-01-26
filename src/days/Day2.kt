package days
//26 Jan 2026
fun main(){
//    val strs: Array<String> = arrayOf("act","pots","tops","cat","stop","hat")
//    val result = groupAnagrams2(strs)
//    println(result?.joinToString())

//    val str = "biren sharmahe"
//    println(lengthOfLastWord2(str))

    val num = intArrayOf(7,7)
    val k = 1
    val result  = topKFrequent2(num,k)
    println(result.joinToString())

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


/**
 * Loop runs n times : O (n)
 *
 *
 * val sorted = map.entries.sortedByDescending { it.value }
 *
 * Sorting m elements : O(m log m)
 *
 * for (i in 0 until k) {} : loop run for k O(k)
 *
 * Total Time complexity: O(n) + O(m log m) + O(k) = O(n log n)
 *
 */
fun topKFrequent(nums: IntArray, k: Int): IntArray {
    val map = HashMap<Int,Int> () //store item and freq

    //update the map
    for (i in nums){
        map[i] = map.getOrDefault(i,0)+1
    }

    //sorting the map by value
    val sorted = map.entries.sortedByDescending { it.value }
    val list = mutableListOf<Int>()
    for (i in 0..k-1){
        list.add(sorted[i].key)
    }

    return intArrayOf()
}

/**
 * TIME COMPLEXITY
 *
 * for (num in nums) -> O(n)
 *
 * for ((num, freq) in freqMap) -> O(n)
 *
 * out loop: for (i in buckets.size - 1 downTo 0) -> O(n)
 *
 * inner loop :  for (num in buckets[i]) -> O(m)
 *
 * inner + outer O(n+m)
 *
 * TOTAL = O(n) + O(n) + O(m) + O(n) = O(n)
 *
 *
 */
fun topKFrequent2(nums: IntArray, k: Int): IntArray {

    // 1️⃣ Step 1: Build frequency map
    // Key   -> number from nums
    // Value -> how many times it appears
    val freqMap = HashMap<Int, Int>()
    for (num in nums) {
        // Increase count of num by 1
        freqMap[num] = freqMap.getOrDefault(num, 0) + 1
    }

    // 2️⃣ Step 2: Create buckets
    // Index = frequency (1 to n)
    // Value = list of numbers with that frequency
    //
    // We use nums.size + 1 because
    // maximum possible frequency of a number is nums.size
    val buckets = Array(nums.size + 1) { mutableListOf<Int>() }

    // 3️⃣ Step 3: Put numbers into their frequency buckets
    for ((num, freq) in freqMap) {
        // If num appears 'freq' times,
        // add it to bucket[freq]
        buckets[freq].add(num)
    }

    // 4️⃣ Step 4: Collect top k frequent elements
    val result = mutableListOf<Int>()

    // Start from highest possible frequency and go down
    for (i in buckets.size - 1 downTo 0) {

        // Each bucket may contain multiple numbers
        for (num in buckets[i]) {
            result.add(num)

            // Stop once we have collected k elements
            if (result.size == k) {
                return result.toIntArray()
            }
        }
    }

    // This line is technically unreachable if input is valid
    return intArrayOf()
}
