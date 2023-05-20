@ECHO off
SETLOCAL ENABLEDELAYEDEXPANSION

FOR %%f IN (test*_input.txt) DO (
    SET expected_output="%%f"
    CALL SET expected_output=%%expected_output:input=output%%
    CALL CMD /C "java -cp ../out/production/bizonyjo Main" < %%f > out.txt
    CALL COMP !expected_output! out.txt /M
    IF !errorlevel! == 1 GOTO tests_failed
)

ECHO TESTS PASSED
GOTO end

:tests_failed
ECHO TESTS FAILED

:end
