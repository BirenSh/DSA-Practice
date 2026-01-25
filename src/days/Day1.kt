package days

fun main(){
//    val arr = intArrayOf(1, 2, 3, 3)
//    val resullt  = hasDuplicate(arr)
//    println(resullt)

    val heystack = "hellp"
    val needle = "ll"
    val result  = strStr2(heystack,needle)
    println(result)
}

fun hasDuplicate(nums: IntArray): Boolean {
    val seen = HashSet<Int>()
    for (i in nums.indices) {
        if (seen.contains(nums[i])) return true
        seen.add(nums[i])
    }
    return false

}

//28. Find the Index of the First Occurrence in a String of leetcode
fun strStr(haystack: String,needle: String):Int{
    //broutforce method
    for(i in 0 until haystack.length-1){
        val temp = StringBuilder()

        for(j in i..<haystack.length){
            if (j<=needle.length-1){
                temp.append(haystack[j])
            }
        }
        println(temp.toString())

        if(temp.toString()==needle) return i
    }

    return -1
}

fun strStr2(haystack: String,needle: String):Int{
    // optimized Sliding widnow
    var left = 0
    var tempStr = ""
    val k = needle.length
    for (right in 0..haystack.length-k){
        tempStr += haystack[right]

        if (right <= k){
            if (tempStr==needle) return left
            tempStr.removeRange(right,right-(k-1))
        }
        left = right
    }
    return -1
}
