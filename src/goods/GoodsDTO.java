package goods;

public class GoodsDTO {

public GoodsDTO() {
	
}
public GoodsDTO(int code, int section, String brand, String name, int cost, double dc, double finalCost,
			String logtime) {
		super();
		this.code = code;
		this.section = section;
		this.brand = brand;
		this.name = name;
		this.cost = cost;
		this.dc = dc;
		this.finalCost = finalCost;
		this.logtime = logtime;
	}
public int getSection() {
	return section;
}
public void setSection(int section) {
	this.section = section;
}
public int getCode() {
	return code;
}
public void setCode(int code) {
	this.code = code;
}
public String getBrand() {
	return brand;
}
public void setBrand(String brand) {
	this.brand = brand;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public int getCost() {
	return cost;
}
public void setCost(int cost) {
	this.cost = cost;
}
public double getDc() {
	return dc;
}
public void setDc(double dc) {
	this.dc = dc;
}
public double getFinalCost() {
	return finalCost;
}
public void setFinalCost(double finalCost) {
	this.finalCost = finalCost;
}
public String getLogtime() {
	return logtime;
}
public void setLogtime(String logtime) {
	this.logtime = logtime;
}
int code;
@Override
public String toString() {
	return "GoodsDTO [section=" + section + ", code=" + code + ", brand=" + brand + ", name=" + name + ", cost=" + cost
			+ ", dc=" + dc + ", finalCost=" + finalCost + ", logtime=" + logtime + "]";
}
int section;
String brand;
String name;
int cost;
double dc;
double finalCost;
String logtime;
}
