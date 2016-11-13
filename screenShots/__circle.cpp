#include <GL/gl.h>	
#include <GL/glut.h>
#include <bits/stdc++.h>
using namespace std;
void createPoint(std::vector<pair<int,int> > & v)
{
	glColor3f(0.0,0.0,0.5,0.0);
	glPointSize(1);
	gluOrtho2D(1,768,1,1024);
	for (int i = 0; i < v.size(); ++i)
		glVertex2i(i.first,i.second);
	glEnd();
	glFlush();
}
int main(int argc, char  *argv[])
{
	/* code */
	glutInit(&argc,argv);
	glutInitDisplatMode(GLUT_SINGLE|GLUT_RGB);
	glutInitWindowPostion(0,0);
	glutInitWindowSize(1024,768);
	glutCreateWindow("Window Name");
	glClearColor(1,0,0,1);
	glClear(GL_COLOR_BUFFER_BIT);
	glFlush();
	std::vector<pair<int,int> > v(1,make_pair(20,30));
	createPoint(v);
	return 0;
}