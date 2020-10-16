#include <iostream>
#include <vector>
#include <unordered_map>

using namespace std;

class Solution
{
public:
    /*
     * Version 1. Declare an STL hashmap to store the appeared numbers. if a number can't be put
     * into the map any longer, return it.
     */
    int findRepeatNumber(vector<int>& nums)
    {
        unordered_map<int, int> m;
        int r = 0xFFFFFFFF;
        for(int n : nums)
        {
            if(!m.emplace(n, 1).second)
            {
                r = n;
                break;
            }
        }
        return r;
    }
};

int main()
{
    vector<int> nums = {2, 3, 1, 0, 2, 5, 3};
    Solution obj;
    cout << obj.findRepeatNumber(nums) << endl;
    return 0;
}
