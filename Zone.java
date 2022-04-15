class Zone {

    protected WaterStage etat = WaterStage.DRY;

    protected void flood() {
        switch (etat) {
            case DRY:
                etat = WaterStage.SUBMERGED;
                break;
            case SUBMERGED:
                etat = WaterStage.FLOODED;
                break;
            case FLOODED:
                break;
        }
    }

    public boolean isFlooded() {
        return etat.equals(WaterStage.FLOODED);
    }

    public boolean isSubmerged() {
        return etat.equals(WaterStage.SUBMERGED);
    }

    public boolean isDry() {
        return etat.equals(WaterStage.DRY);
    }
}