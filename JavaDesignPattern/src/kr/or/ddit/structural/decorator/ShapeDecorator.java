package kr.or.ddit.structural.decorator;

public abstract class ShapeDecorator implements Shape{
	protected Shape decoratedShape;
	
	public ShapeDecorator(Shape decorateShape) {
		this.decoratedShape = decorateShape;
	}
	
	/*@Override
	public void draw() {
		decoratedShape.draw();
		
	}  없어도될거같아 abstract 써줄거거든 */

}