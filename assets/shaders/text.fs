#version 110

varying vec2 tc;

uniform sampler2D tex;
uniform vec4 vColor;

void main()
{
	gl_FragColor = vec4(vColor.x, vColor.y, vColor.z, texture2D(tex,tc).a);
}
