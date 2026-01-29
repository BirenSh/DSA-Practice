package days

fun main(){
    val arr  = intArrayOf(1,2,3,4,5,6)
    val numArray = NumArray(arr)
    println( numArray.sumRange(0,2))
    println( numArray.sumRange(2,5))
}

class NumArray(nums: IntArray) {

    private val prefix: IntArray

    init {
        prefix = IntArray(nums.size + 1)

        for (i in nums.indices) {
            prefix[i + 1] = prefix[i] + nums[i]
        }
        println(prefix.joinToString())
    }


    fun sumRange(left: Int, right: Int): Int {
        return prefix[right + 1] - prefix[left]
    }
}