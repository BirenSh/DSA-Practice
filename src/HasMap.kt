import kotlin.math.max

fun main(){
//    val str = "Leetcode"
//    println(firstUniqueChar(str))

//   val  nums = intArrayOf(2,7,11,15)
//   val target = 18
//
//    val resutl = twoSum2(nums,target)
//    println(resutl.joinToString())

    //Anagram
//    val s = "anagram"
//    val t = "nagaram"
//    println(validAnagram(s,t))


    //LongestSubString without repeat
//    val str = "abba"
//    longSub(str)

    //ROMAN
    val str = "MCMXCIV"
    println(romanToInt(str))
}

//First Non-Repeating Character
fun firstUniqueChar(string: String): Int{
    val has = HashMap<Char,Int>()

    for (i in string.indices){
        has[string[i]] = has.getOrDefault(string[i],0)+1
    }

    for (i in string.indices){
        if (has[string[i]] == 1) return i
    }
    return -1
}

//two sum hasmap, Return indices
fun twoSum2(arr: IntArray, targetValue: Int): IntArray{
    //broutforce
//    for (i in arr.indices){
//        for (j in i..< arr.size-1){
//            if (arr[i]+arr[j]==targetValue) return intArrayOf(i,j)
//        }
//    }


    //optimizeHasMpa
    val map = HashMap<Int,Int>()

    for (i in arr.indices){
        val comp = targetValue - arr[i]
        if (map.containsKey(comp)) {
            return intArrayOf(map[comp]!!, i)
        } else map[arr[i]] = i
    }

    return intArrayOf()
}

//Valid Anagram
fun validAnagram(str1: String,str2: String): Boolean{

    //O(n log n)
//    val sortedS1 = str1.toCharArray().sorted()
//    val sortedS2 = str2.toCharArray().sorted()
//    return (sortedS2 == sortedS1)

//    //borut force
//    val str2Array = str2.toMutableList() // convert to ['a','b','c']
//    for (i in str1.indices){
//        if (str2Array.contains(str1[i])){       // check for char contain in list
//            str2Array.remove(str1[i])   // if yes then keep removing
//        }else {
//            return false                        // if not that mean , char in both string not same
//        }
//    }
//    return  str2Array.isEmpty()         // if char in bot str has same char, it mean list is empty after removing char


    //hasMpa 2nd approch optimized
    val hasfirst = HashMap<Char,Int>()
    for (i in str1.indices){
        hasfirst[str1[i]] =  hasfirst.getOrDefault(str1[i],0)+1
    }

    for (i in str2.indices){
        hasfirst[str2[i]] = hasfirst.getOrDefault(str2[i],0) -1 // reduce the count if match found else add
        if (hasfirst[str2[i]]!! < 0) return false     //if match count is less than 0 means char is not exist in map
    }

     return true


    //3rd approch otpimized
//    val hasSecond = HashMap<Char,Int>()
//    for (i in str1.indices){
//        hasfirst[str1[i]] =  hasfirst.getOrDefault(str1[i],0)+1
//    }
//
//    for (i in str2.indices){
//        hasfirst[str2[i]] = hasfirst.getOrDefault(str2[i],0) -1
//        if (hasfirst[str2[i]]!! < 0) return false
//        hasSecond[str2[i]] =  hasSecond.getOrDefault(str2[i],0)+1
//    }
//
//    return true
}


//Longest substring
fun longSub(str: String){

    //Brout Force
//    var maxValue = 0
//    for (i in str.indices){
//        val tempSet = HashSet<Char>()
//        var count = 0
//        for (j in i until  str.length){
//            if (tempSet.contains(str[j])) break
//            tempSet.add(str[j])
//            count++
//        }
//        maxValue = maxOf(maxValue,count)
//    }
//    println(maxValue)

    //optimized
    var maxSize = 0
    var left = 0
    val map = HashMap<Char,Int>()

    for (i in str.indices){
        val currentChar = str[i]

        if (map.containsKey(currentChar) && map[currentChar]!! >= 0){
            left = map[currentChar]!!+1
        }

        map[currentChar] = i
        maxSize = maxOf(maxSize,i-left+1)

    }

    println(maxSize)
}

