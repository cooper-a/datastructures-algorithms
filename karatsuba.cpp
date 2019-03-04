#include <iostream>
#include <cmath>
using namespace std;

int digit_count(int input_int);
int multiply(int x, int y);
int ipow(int base, int exp);

int main(){
  int x = 12345;
  int y = 678;
  multiply(x, y);
}

int multiply(int x, int y){
  int x_count = digit_count(x);
  int y_count = digit_count(y);
  int max_count = max(x_count, y_count);
  int a = x / ipow(10, (max_count / 2));
  int b = x % ipow(10, (max_count / 2));
  int c = y / ipow(10, (max_count / 2));
  int d = y % ipow(10, (max_count / 2));
  if(x / 10 == 0 || y / 10 == 0){
    return x * y;
  }
  // else{
  //   int first = multiply(a, c);
  //   int second = multiply(b, d);
  //   int third = multiply(a + b, c + d) - first - second;
  //   return
  // }
  cout << "A: " << a << endl;
  cout << "B: " << b << endl;
  cout << "C: " << c << endl;
  cout << "D: " << d << endl;
  return 0;
}

int digit_count(int input_int){
  return log10(input_int) + 1;
}

int ipow(int base, int exp)
{
    int result = 1;
    for (;;)
    {
        if (exp & 1)
            result *= base;
        exp >>= 1;
        if (!exp)
            break;
        base *= base;
    }

    return result;
}
