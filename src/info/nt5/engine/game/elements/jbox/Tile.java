package info.nt5.engine.game.elements.jbox;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;

import info.nt5.engine.graphics.Camera;
import info.nt5.engine.graphics.Color;
import info.nt5.engine.graphics.Texture;
import info.nt5.engine.graphics.shader.Mesh;
import info.nt5.engine.graphics.shader.Shader;
import info.nt5.engine.graphics.shader.VertexQuad;
import info.nt5.engine.math.Matrix4f;
import info.nt5.engine.math.Vector2f;
import info.nt5.engine.math.Vector3f;

public class Tile {

	private Mesh mesh;
	private VertexQuad quad;
	private Texture texture;

	private Matrix4f ml_matrix;
	private Vector3f position = new Vector3f(0f);
	private Camera camera;

	private Body box;

	public Tile(Color color, Vector2f size, World world, BodyDef boxdef, FixtureDef boxFixture) {
		this.setUpGeometry(size, color);

		this.box = world.createBody(boxdef);
		this.box.createFixture(boxFixture);

		this.ml_matrix = new Matrix4f();
		this.camera = Camera.worldCamera;
	}

	public Tile(Color color, Vector2f size, Vector2f position, World world) {
		this.setUpGeometry(size, color);

		this.ml_matrix = new Matrix4f();
		this.camera = Camera.worldCamera;

		BodyDef boxdef = new BodyDef();
		boxdef.position.set(position.x, position.y);
		boxdef.type = BodyType.DYNAMIC;

		PolygonShape boxShape = new PolygonShape();
		boxShape.setAsBox(size.x, size.y);

		this.box = world.createBody(boxdef);

		FixtureDef boxFixture = new FixtureDef();
		boxFixture.density = 1;
		boxFixture.shape = boxShape;

		this.box.createFixture(boxFixture);
	}

	private void setUpGeometry(Vector2f size, Color color) {
		this.quad = new VertexQuad(size.x, size.y);
		this.mesh = new Mesh(

				this.quad.getVertices(),

				this.quad.getTexCoords(),

				this.quad.getNormals(),

				this.quad.getIndices()

		);
		this.texture = Texture.fromColor(color, 1, 1);
	}

	public Body getBody() {
		return this.box;
	}

	public VertexQuad getQuad() {
		return this.quad;
	}

	public void setCamera(Camera camera) {
		this.camera = camera;
	}

	public void setMl_matrix(Matrix4f ml_matrix) {
		this.ml_matrix = ml_matrix;
	}

	public void update() {
		position.x = box.getPosition().x;
		position.y = box.getPosition().y;

		ml_matrix = Matrix4f.translate(position).multiply(

				Matrix4f.rotateZ((float) Math.toDegrees(box.getAngle()))

		);
	}

	public void render() {
		texture.bind();
		Shader.geometryShader.bind();
		Shader.geometryShader.setUniformMat4f("vw_matrix", camera.getViewMatrix());
		Shader.geometryShader.setUniformMat4f("pr_matrix", camera.getProjectionMatrix());
		Shader.geometryShader.setUniformMat4f("ml_matrix", ml_matrix);
		mesh.render();
		Shader.geometryShader.unbind();
		texture.unbind();
	}

	public void dispose() {
		texture.dispose();
		mesh.dispose();
	}

}
