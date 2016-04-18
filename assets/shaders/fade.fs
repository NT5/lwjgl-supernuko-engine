#version 110

uniform float time;
uniform vec4 color;

void main()
{
	if (time > 1.0)
		discard;
	gl_FragColor = vec4(color.x, color.y, color.z, 1.0 - time);
}