#include<iostream>
#include <time.h>
using namespace std;

void Quick_Sort(int a[],int,int);
int partition(int a[],int,int);
int main()
{
    int a[100000];
    int size;
    clock_t x, y, z;
    float t;
    
    cout<<"Enter size of array:"<<endl;
    cin>>size;
    cout<<"Enter array elements:"<<endl;
    for(int i=0;i<size;i++)
    {
        cin>>a[i];
    }
    int lb=0;
    int ub=size-1;
    x = clock();
    Quick_Sort(a,lb,ub);
    y = clock();
    z = y - x;
    t = (float)z / CLOCKS_PER_SEC;
    printf("%f", t);
    cout<<endl;
    for(int i=0;i<size;i++)
    {
        cout<<a[i]<<" ";
    }
    return 0;
}
int partition(int a[],int lb,int ub)
{
    int i=lb-1;
    int pivot=a[ub];
    for(int j=lb;j<ub;j++)
    {
        if(a[j]<=pivot)
        {
            i++;
            int temp=a[i];
            a[i]=a[j];
            a[j]=temp;
        }
    }
    i++;
    int temp=a[ub];
    a[ub]=a[i];
    a[i]=temp;
    return i;
}  
void Quick_Sort(int a[],int lb,int ub)
{
    if(lb<ub)
    {
        int q=partition(a,lb,ub);
        Quick_Sort(a,lb,q-1);
        Quick_Sort(a,q+1,ub);
    }
}