#include <iostream>
#include <vector>

using namespace std;

class Solution
{
public:
    /*
     * Version 2, use an array to simulate hashmap, it can be more efficient. It is because the
     * range of numbers is [0, n - 1], otherwise I would prefer hashmap.
     */
    int findRepeatNumber(vector<int>& nums)
    {
        int* map = new int[nums.size()]();
        for(int n : nums)
        {
            if(map[n])
            {
                return n;
            }
            map[n]++;
        }
        return -1;
    }
};

int main()
{
    vector<int> nums = {2, 3, 1, 0, 2, 5, 3};
    Solution obj;
    cout << obj.findRepeatNumber(nums) << endl;
    return 0;
}
