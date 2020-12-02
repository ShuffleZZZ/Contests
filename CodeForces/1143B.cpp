#include <iostream>
#include <string>
#include <math.h>

// https://codeforces.com/problemset/problem/1143/B

int rank(int n) {
    int i = 0;
    while(n > 0) {
        ++i;
        n /= 10;
    }

    return i;
}

long long mul(long long a) {
	int res = 1;
	while(a > 0) {
		res *= a % 10;
		a /= 10;
	}

	return res;
}

int main() {
    int n;
    std::cin >> n;
    int s = rank(n);
    long long ans = mul(n);
    int res = n;
    for (int i = 1; i <= s; i++) {
        long long pow1 = pow(10, i);
        long long a = (n / pow1 - 1) * pow1  + std::stoll(std::string(i, '9'));
        long long m = mul(a);
        if (m > ans) {
            ans = m;
            res = a;
        }
    }

    std::cout << ans;
}
