package thomashan.github.io.payroll.transaction.add

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import thomashan.github.io.payroll.classification.CommissionedClassification
import thomashan.github.io.payroll.method.HoldMethod
import thomashan.github.io.payroll.schedule.BiweeklySchedule
import thomashan.github.io.payroll.transaction.Transaction
import thomashan.github.io.payroll.transaction.TransactionTests
import thomashan.github.io.payroll.transaction.add.AddCommissionedEmployee

class AddCommissionedEmployeeTests implements TransactionTests {
    private Transaction transaction
    private String name = "Commissioned"
    private String address = "CommissionedHome"
    private double salary = 1000.0
    private double commissionRate = 20.0

    @BeforeEach
    void setUp() {
        transaction = new AddCommissionedEmployee(employeeId, name, address, salary, commissionRate)
    }

    @Test
    void "add commissioned employee should return correct employee name"() {
        transaction.execute()

        assert payrollDatabase.getEmployee(employeeId).name == name
    }

    @Test
    void "add commissioned employee should return correct employee address"() {
        transaction.execute()

        assert payrollDatabase.getEmployee(employeeId).address == address
    }

    @Test
    void "add commissioned employee should return correct payment classification"() {
        transaction.execute()

        assert payrollDatabase.getEmployee(employeeId).paymentClassification instanceof CommissionedClassification
    }

    @Test
    void "add commissioned employee should return correct salary"() {
        transaction.execute()
        CommissionedClassification commissionedClassification = (CommissionedClassification) payrollDatabase.getEmployee(employeeId).paymentClassification

        assert commissionedClassification.salary == salary
    }

    @Test
    void "add commissioned employee should return correct commission rate"() {
        transaction.execute()
        CommissionedClassification commissionedClassification = (CommissionedClassification) payrollDatabase.getEmployee(employeeId).paymentClassification

        assert commissionedClassification.commissionRate == commissionRate
    }

    @Test
    void "add commissioned employee should return correct payment schedule"() {
        transaction.execute()

        assert payrollDatabase.getEmployee(employeeId).paymentSchedule instanceof BiweeklySchedule
    }

    @Test
    void "add commissioned employee should return correct payment method"() {
        transaction.execute()

        assert payrollDatabase.getEmployee(employeeId).paymentMethod instanceof HoldMethod
    }
}
