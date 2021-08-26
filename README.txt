This projects purpose was to simulate multithreading without using any of the built in libraries in order to demonstrate understanding. Please see the project 2 pdf for exact instructions given for the project.
If you would like to change the matrix sizes below you will find a more intensive test for the program simply copy the below code and use it to replace the following:
#define M 2
#define N 3
#define O 2

int main()
{
  int value, valParent;
	int matrix1[M][N] = { {1, 2, 3}, {4, 5, 6} }; /*M rows x N columns*/
	int matrix2[N][O] = { {1, 2}, {3,4}, {5,6} }; /* N x O */
	int Product[M][O]; /* M x O */
  --------
  with:
#define M 5
#define N 3
#define O 3

int main()
{
  int value, valParent;
	int matrix1[M][N] = { {1,2,3}, {4,5,6}, {7,9,8}, {11,12,13}, {14,15,16} }; /*M rows x N columns*/
	int matrix2[N][O] = { {1,2,7}, {3,4,8}, {5,6,9} }; /* N x O */
	int Product[M][O]; /* M x O */
  -------
  You can also test with your own data sizes where M and O determine the size of the matrix (M*O), This program has been tested with matrixes of size 100x100.
If you have any questions please email me, and thanks so much for reading!
Ahsif Safdar 
Ahsif.Safdar@gmail.com
Thank you for your time
