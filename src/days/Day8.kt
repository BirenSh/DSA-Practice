package days

fun main(){
    val nums = intArrayOf(9,1,4,7,3,-1,0,5,8,-1,6)
    val nums2= intArrayOf(2,20,4,10,3,4,5)
    val result = longestConsecutive2(nums2)
    println(result)
}

fun longestConsecutive(nums: IntArray): Int {
    if (nums.isEmpty()) return 0

    val sorted = nums.sorted()
    var maxLen = 1
    var currLen = 1

    for (i in 1..nums.size-1){
        when{
            sorted[i] == sorted[i-1] -> continue

            sorted[i] == sorted[i-1]+1 -> {
                currLen++
                maxLen = maxOf(maxLen,currLen)
            }

            else->{
                currLen = 1
            }
        }
    }

    return maxLen

}

fun longestConsecutive2(nums: IntArray): Int {
    val set = nums.toHashSet()
    var maxLen = 0

    for (i in nums){

        if (!set.contains(i-1)){
            var curr = i
            var len = 1

            while (set.contains(curr+1)){
                curr++
                len++
            }

            maxLen = maxOf(maxLen,len)
        }

    }
    return maxLen
}


