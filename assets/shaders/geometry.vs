#version 110

attribute vec4 position;
attribute vec2 texcoord;

uniform mat4 pr_matrix;
uniform mat4 vw_matrix;
uniform mat4 ml_matrix;

varying vec2 tc;

void main()
{
	gl_Position = pr_matrix * vw_matrix * ml_matrix * position;
	tc = texcoord;
}