import kotlin.math.max

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    //find the index of
    val nums = intArrayOf(1, 3, 2, 6, -1, 4, 1, 8, 2)
    val target = 14
    val str = "hello"

        val s = "anagram"
    val t = "nagaram"

//    val anagram:Array<String> = arrayOf("eat","tea","tan","ate","nat","bat")
//    val reuslt = topKFrequent(nums,2)

//    println(reuslt)

//    encode(listOf("Hello","Biren"))
    println(arvSum(nums,3))
}


fun twoSum(nums: IntArray, target: Int): IntArray {
    /**
     * val nums = intArrayOf(2, 7, 11, 15)
     *
     * val target = 9
     */
    val hashMap = mutableMapOf<Int, Int>() // Key: Number, Value: Index

    for (i in nums.indices) {
        val complement = target - nums[i]

        // 1. Check: Have we seen the needed partner before?
        if (hashMap.containsKey(complement)) {
            // YES! Return the index of the partner and current index
            return intArrayOf(hashMap[complement]!!, i)
        }

        // 2. Remember: If not found, "write" current number and its index in our map
        hashMap[nums[i]] = i
    }
    return intArrayOf() // Return empty if no solution is found
}

/**
 * The Problem: You are given an array of numbers.
 *
 * Return true if any value appears at least twice, and false if every element is distinct.
 */

fun hasDuplicateBruteForce(nums: IntArray): Boolean {
    /**
     * Input: [1, 2, 3, 9] → Output: true
     */
    val seen = mutableSetOf<Int>()
    for (i in nums){
        if (seen.contains(i)) return true
        else seen.add(i)
    }

    return false
}


fun isPalindrome(str: String): Boolean{
    /**
     * Input: "racecar" → Output: true
     *
     * Input: "hello" → Output: false
     */
    var left = 0
    var right = str.length-1

    while (left<right){
        if (str[left] != str[right]){
            return false

        }else {
            left++
            right --
        }
    }
    return true
}


fun sortArrayByParity(nums: IntArray): IntArray {

    /**
     * "You are given an array of integers. Move all the even numbers to the beginning and all the odd numbers to the end. Do this without using extra memory."
     */
    var left = 0
    var right = nums.size - 1

    while (left < right) {
        // 1. Move left pointer if it's pointing to an Even number
        while (left < right && nums[left] % 2 == 0) {
            left++
        }

        // 2. Move right pointer if it's pointing to an Odd number
        while (left < right && nums[right] % 2 != 0) {
            right--
        }

        // 3. If they haven't met, it means we found a pair to swap!
        if (left < right) {
            val temp = nums[left]
            nums[left] = nums[right]
            nums[right] = temp

            // After swapping, move both pointers one step
            left++
            right--
        }
    }
    return nums
}


fun isAnagram(s: String, t: String): Boolean {
//    val s = "anagram"
//    val t = "nagaram"
//    val sToChar = s.toCharArray()
//    val tToChar = t.toCharArray()
//
//    sToChar.sort()
//    tToChar.sort()
//    println(sToChar)
//    println(tToChar)
//    if (sToChar.contentEquals(tToChar)) return true else return false
    if (s.length != t.length) {
        return false
    }

    val count = IntArray(26)
    for (i in s.indices) {
        count[s[i] - 'a']++
        count[t[i] - 'a']--
    }


    for (value in count) {
        if (value != 0) {
            return false
        }
    }
    return true
}

fun groupAnagrams(strs: Array<String>) {
//    val map = mutableMapOf<String, MutableList<String>>()
//    for(i in strs){
//        val sorted = i.toCharArray().sorted().joinToString("")
//        map.getOrPut(sorted){
//            mutableListOf()
//        }.add(i)
//    }
//    println(map)

    val map = mutableMapOf<List<Int>, MutableList<String>>()

    for (i in strs){
        val count = MutableList(26) { 0 }
        for (j in i){
            count[j - 'a']++
        }
        map.getOrPut(count) { mutableListOf() }.add(i)

    }

    println(map.values.toList())
}

fun topKFrequent(nums: IntArray, k: Int): IntArray {

//    val nums = intArrayOf(1, 2, 1, 6, 2, 2,10)
//
    val map = mutableMapOf<Int, Int>()

    // 1. Frequency Map: O(n)
    // Counts how many times each number appears
    for (num in nums) {
        map[num] = (map[num] ?: 0) + 1
    }

    println(map)
    // 2. The Bucket Trick: O(n)
    // Create an array where index = frequency
    // Size is nums.size + 1 because a number could appear up to n times
    val buckets = Array(nums.size + 1) { mutableListOf<Int>() }

    for ((num, frequency) in map) {
        buckets[frequency].add(num)
    }

    println(buckets.joinToString())

    // 3. Collect Results: O(n)
    // Iterate backwards from the highest frequency bucket
    val result = mutableListOf<Int>()
    for (i in buckets.size - 1 downTo 0) {
        for (num in buckets[i]) {
            result.add(num)
            println(result.joinToString())

            if (result.size == k) return result.toIntArray()
        }
    }


    return result.toIntArray()
}

fun maxDistance(args:Array<String>):Int {


    val n = args.size

//max dis from the 1st house
    var maxDist = 0
    for (i in n - 1 downTo 1) {
        if (args[i] != args[0]) {
            maxDist = i
            break
        }
    }

//max dist from the last house
    for (i in 0 until n - 1) {
        if (args[i] != args[n - 1]) {
            maxDist = max(maxDist, n - 1 - i)
            break
        }
    }

    return maxDist

}


fun encode(strs: List<String>) {
    val incoStr = StringBuilder()
    for(i in strs){
        incoStr.append(i.length).append('#').append(i)
    }
    println(incoStr)
    decode(incoStr.toString())

}

fun decode(str: String){
//    5#Hello5#Biren
    val strList = mutableListOf<String>()
    var i  = 0

    while (i < str.length){
        var j = i

        while(str[j] != '#'){
            j++
        }
        val length = str.substring(i,j).toInt()
        val startStr = j+1
        val endStr = startStr + length

        strList.add(str.substring(startStr,endStr))
        i = endStr
    }
    val list = mutableListOf<Int>()
    println(strList)

}

//sliding windows fix size
fun maxSum(arr:IntArray,k:Int):Int{
    var maxSum = 0

    /* for(i in 0..arr.size-k){
         var sum = 0

         for(j in i until i+k){
             sum += arr[j]

             maxSum = maxOf(sum,maxSum)
         }
         println(sum)
     }
     */
    var windowSum = 0
    for (i in arr.indices){
        windowSum += arr[i]

        if(i >= k-1){
            maxSum = maxOf(windowSum,maxSum)
            val remove = i - (k-1)
            windowSum -= arr[remove]
        }
    }

    return maxSum
}


fun arvSum(arr: IntArray,k: Int){
    val list1 = FloatArray(arr.size - (k-1))
    var windSum = 0
    var index = 0
    for (i in arr.indices){
        windSum += arr[i]

        if (i >= k - 1) {
            list1[index++] = windSum.toFloat() / k
            windSum -= arr[i - (k - 1)]
        }
    }

    println(list1.joinToString())
}

fun maxNumVowel(arr: String,k: Int):Int{

    var vCount = 0
    var maxCount = 0
    val tempStr = StringBuilder()
    val vowel = arrayListOf<Char>('a','e','i','o','u')
    for (i in arr.indices){
       if (vowel.contains(arr[i])) vCount ++
        tempStr.append(arr[i])

        if (i >= k-1){
            maxCount = maxOf(maxCount,vCount)
            val removeChar = i - (k-1)
            tempStr[removeChar]
            tempStr.removeRange(i,removeChar)
        }
    }
    return maxCount

}



fun buildPrefixSum(arr:IntArray):IntArray{
    val list = IntArray(arr.size)
    var sum = 0
    for(i in arr.indices){
        sum += arr[i]
        list[i] = sum
    }

    return list
}

fun countEven(arr:IntArray):Int{

    var sum = 0

    for(i in arr){
        if(i %2 == 1){
            sum++
        }
    }

    return sum

}


fun countSum(arr:IntArray):IntArray{
    val list = IntArray(arr.size)

    /* for(i in 0..arr.size-1){
         var sum = 0

         for(j in 0..arr.size-1){
             if(arr[i] != arr[j]){
                 sum = sum+(arr[i]+arr[j])
             }
         }

         list[i] = sum
     }
     */

    var totlaSum = 0
    for(i in arr){
        totlaSum += i
    }
    println(arr.size-1)

    for(i in arr.indices){
        list[i] = arr[i]*(arr.size-1) + (totlaSum-arr[i])

    }
    return list
}



fun subSum(arr:IntArray):IntArray{
    //|1-2| + |1-3| = 3,
    //[1,2,3]
    val list = IntArray(arr.size)

    for(i in 0..arr.size-1){
        var sum = 0

        for(j in 0..arr.size-1){
            if(arr[i] != arr[j]){
                val sub = arr[i]-arr[j]
                sum += sub
            }
        }

        list[i] = sum
    }
    return list
}


fun maxSume(arr:IntArray,k:Int):Int{
    var maxSum = 0

    /* for(i in 0..arr.size-k){
         var sum = 0

         for(j in i until i+k){
             sum += arr[j]

             maxSum = maxOf(sum,maxSum)
         }
         println(sum)
     }
     */

    var windowSum = 0

    for(i in 0..arr.size-1){
        windowSum += arr[i]

        if(i >= k-1){
            println(windowSum)
            maxSum = maxOf(windowSum,maxSum)

            val toRemove = i - (k-1)
            windowSum -= arr[toRemove]
        }


    }


    return maxSum
}
