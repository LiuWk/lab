package pattern.flyweight;

//白色棋子类：具体享元类
class WhiteIgoChessman extends IgoChessman {
	@Override
    public String getColor() {
		return "白色";
	}
}
