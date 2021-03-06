package thomashan.github.io.payroll.transaction

import thomashan.github.io.payroll.InMemPayrollDatabase
import thomashan.github.io.payroll.PayrollDatabase

trait Transaction {
    final PayrollDatabase payrollDatabase = InMemPayrollDatabase.instance

    abstract void execute()
}
