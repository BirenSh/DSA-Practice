package days

fun main(){
    val arr  = intArrayOf(1,2,2,3,4,5,6)
    println(subarraySum2(arr,5))
}

//560. Subarray Sum Equals K


/**
 * Time : O(n^2)
 */
fun subarraySum(nums: IntArray, k: Int): Int {
    var count = 0
    for (i in 0 until nums.size){
        var sume = 0
        for (j in i until nums.size){
            sume+= nums[j]

            if (sume==k) count++
        }
    }

    return count
}

fun subarraySum2(nums: IntArray, k: Int): Int {
    val map = HashMap<Int,Int>()
    var sum = 0
    var count = 0
    map[0] = 1
    for (i in nums){
        sum += i

        if (map.containsKey(sum-k)){
            println("$sum - $k")
            count += map[sum-k]!!
        }
        map[sum] = map.getOrDefault(sum,0)+1
    }
    println(map)
    return count
}