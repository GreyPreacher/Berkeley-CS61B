package byog.Core;

class Position extends Coordinate{
    Position(int x, int y){
        super(x, y);
    }

    Position(){
        super();
    }

    //@Override
    public boolean equal(Object obj){
        // If this is a quote of the same object, return true
        if (this == obj){
            return true;
        }

        // If this is null, return false
        if (this == null){
            return false;
        }

        // If the classes of THIS and obj are not the same, return false
        if (getClass() != obj.getClass()){
            return false;
        }

        // If of the same class, compare if the content is the same
        Position other = (Position) obj;
        return other.x == this.x && other.y == this.y;
    }

    Position up(){
        return new Position(x, y + 1);
    }

    Position down(){
        return new Position(x, y - 1);
    }

    Position left(){
        return new Position(x - 1, y);
    }

    Position right(){
        return new Position(x + 1, y);
    }
}
