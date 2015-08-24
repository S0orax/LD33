#version 120

uniform sampler2D tex;
uniform float time;

varying vec2 texCoord;

vec4 zenith = vec4(1.0,1.0,1.0,1.0);
vec4 twilight = vec4(0.7,0.4,0.2,1.0);

void main() {
	gl_FragColor = texture2D(tex, texCoord);
	gl_FragColor = gl_Color * (vec4)gl_FragColor.rgb;
}