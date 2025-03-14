export default class Cell {
    private id: number;
    private check: boolean;
    private value: string;

    constructor (id:number, value:string) {
        this.id = id;
        this.check = false;
        this.value = value;
    }

    public getId() {
        return this.id;
    }

    public getValue() {
        return this.value;
    }

    public isChecked() {
        return this.check;
    }

    public putValueInCell(value:string) {
        if (!this.check) {
            this.checking();
            this.value = value;
        } else {
            return;
        }
    }

    private checking () {
        this.check = true;
    }
}