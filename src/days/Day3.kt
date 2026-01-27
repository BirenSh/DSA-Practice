package days


fun main(){
    val num = intArrayOf(1,2,3,4)
    val resutl = productExceptSelf3(num)
    println(resutl.joinToString())
}

/**
 * Time complex n*n: O(n^2)
 *
 * Space Complex: O(1)
 */
fun productExceptSelf(nums: IntArray): IntArray {
    val list = mutableListOf<Int>()

    for (i in 0..nums.size - 1) {
        var result = 1
        for (j in 0..nums.size - 1) {
            if (j != i) {
                result *= nums[j]
            }
        }
        list.add(result)
    }
    return list.toIntArray()
}


/**
 * Time complex: O(n) + O(n) +O(n) = O(n) two loop but not nested
 *
 * Space Complex: left + right + result  = O(n)+O(n)+O(n) = O(n)
 */
fun productExceptSelf1(nums: IntArray): IntArray {
    val n = nums.size
    val left = IntArray(n) //prefix
    val right = IntArray(n) //postfix
    val result = IntArray(n)

    // left products
    left[0] = 1
    for (i in 1 until n) {
        left[i] = left[i - 1] * nums[i - 1]
    }
    println(left.joinToString())

    // right products
    right[n - 1] = 1
    for (i in n - 2 downTo 0) {
        right[i] = right[i + 1] * nums[i + 1]
    }
    println(right.joinToString())


    // result
    for (i in 0 until n) {
        result[i] = left[i] * right[i]
    }
    println(result.joinToString())


    return result
}


/**
 * Time complex: O(n) + O(n) = O(n) two loop but not nested
 *
 * Space Complex: O(1) because output array ignored here
 */
fun productExceptSelf3(nums: IntArray): IntArray {
    val result  = IntArray(nums.size)

    result[0] = 1
    for (i in 1 until nums.size){
        result[i] = result[i-1] * nums[i-1]
    }
    println(result.joinToString ())

    var rightProduct = 1
    for (i in nums.size-1 downTo 0){
        result[i] *= rightProduct
        rightProduct *= nums[i]

    }

    return result
}

