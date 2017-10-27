package caster.demo.code.common.other;

public enum ExcelType {
    XLS(".xls"),
    XLSX(".xlsx")
    ;

    private String suffix;

    ExcelType(String suffix) {
        this.suffix = suffix;
    }

    public String getSuffix() {
        return suffix;
    }

}
