#version 110

varying vec2 tc;

uniform sampler2D tex;

void main()
{
	gl_FragColor = vec4(texture2D(tex,tc).rgba);
}
