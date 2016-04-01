#version 330 core

layout (location = 0) out vec4 color;

in DATA
{
	vec2 tc;
} fs_in;

uniform sampler2D tex;
uniform vec4 vColor;

void main()
{
	vec4 text = texture(tex,fs_in.tc);
	color = vec4(vColor.x, vColor.y, vColor.z, text.a);
}