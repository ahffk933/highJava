package kr.or.ddit.structural.decorator;

public class RedShapeDecorator extends ShapeDecorator{

	public RedShapeDecorator(Shape decoratedShape) {
		super(decoratedShape);
	}
	
	@Override
	public void draw() {  //기본기능
		decoratedShape.draw();  
		setRedBorder(decoratedShape);  // 경계선 빨간색으로 칠하는 기능 추가
		
	}
	
	private void setRedBorder(Shape decoreatedShape) {
		System.out.println("경계선 빨간색으로 칠하기");
	}

}
