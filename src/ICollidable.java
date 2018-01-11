public interface ICollidable {
    public boolean collided();
}

class ICollidableWithTerrain implements ICollidable {
    Terrain terrain;
    double x;
    double y;

    public ICollidableWithTerrain(Projectile projectile) {
        this.x = projectile.getX();
        this.y = projectile.getY();
        terrain = Terrain.getInstance();
    }

    @Override
    public boolean collided() {
        if (y >= terrain.getY()[(int) Math.round(x)]) {
            return true;
        }
        return false;
    }
}

class ICollidableWithTank implements ICollidable {

    Tank tank;
    double x;
    double y;
    double radius;

    public ICollidableWithTank(Tank tank, Projectile projectile) {
        this.tank = tank;
        radius = projectile.getRadius();
        this.x = projectile.getXc() + radius;
        this.y = projectile.getYc() + radius;
    }

    @Override
    public boolean collided() {
        double dx = tank.getHitbox().getXc() - x;
        double dy = tank.getHitbox().getYc() - y;
        double distance = Math.sqrt(dx * dx + dy * dy);
        if (distance < tank.getHitbox().getRadius() + radius) {
            return true;
        }
        return false;
    }
}

class ICollidableWithProjectile implements ICollidable {

    @Override
    public boolean collided() {
        return false;
    }
}
