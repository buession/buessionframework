#!/bin/bash

cd "${TRAVIS_BUILD_DIR}/buession-parent"

echo -e "***********************************************"
echo -e "Maven build started at `date`"
echo -e "***********************************************"

mvn clean install -Dmaven.test.skip=true

retVal=$?

echo -e "***************************************************************************************"
echo -e "Maven build finished at `date` with exit code $retVal"
echo -e "***************************************************************************************"

if [ $retVal == 0 ]; then
    echo "Maven build finished successfully."
    exit 0
else
    echo "Maven build did NOT finish successfully."
    exit $retVal
fi