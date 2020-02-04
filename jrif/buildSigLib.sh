#!/bin/sh
####################################
#
# Test jrif.
#
####################################

# Run it as  ./buildSigLib.sh 

echo "Building signatures in java directory. "
cd sig-src/java
jrifc -d /Users/wenyuanma/research/infoflow/JRIFsystem/jrif/sig-classes/ */*.jrif  ../jif/lang/Principal.jrif ../jif/lang/Closure.jrif  ../jif/lang/ActsForProof.jrif


echo "Building signatures in jif directory. "
cd ../jif
cd net
jrifc -d /Users/wenyuanma/research/infoflow/JRIFsystem/jrif/sig-classes/ *.jrif
cd ../runtime/
jrifc -d /Users/wenyuanma/research/infoflow/JRIFsystem/jrif/sig-classes/ *.jrif
cd ../lang/
jrifc -d /Users/wenyuanma/research/infoflow/JRIFsystem/jrif/sig-classes/  AbstractPrincipal.jrif
jrifc -d /Users/wenyuanma/research/infoflow/JRIFsystem/jrif/sig-classes/  Capability.jrif
jrifc -d /Users/wenyuanma/research/infoflow/JRIFsystem/jrif/sig-classes/  ConfPolicy.jrif
jrifc -d /Users/wenyuanma/research/infoflow/JRIFsystem/jrif/sig-classes/  DelegatesProof.jrif
jrifc -d /Users/wenyuanma/research/infoflow/JRIFsystem/jrif/sig-classes/  IntegPolicy.jrif
jrifc -d /Users/wenyuanma/research/infoflow/JRIFsystem/jrif/sig-classes/  LabelUtil.jrif
jrifc -d /Users/wenyuanma/research/infoflow/JRIFsystem/jrif/sig-classes/  PrincipalUtil.jrif
jrifc -d /Users/wenyuanma/research/infoflow/JRIFsystem/jrif/sig-classes/  TransitiveProof.jrif
cd ../../..

echo "Building libraries in jif directory. "

cd lib-src/jif/lang/
jrifc -d /Users/wenyuanma/research/infoflow/JRIFsystem/jrif/lib-classes/ *.jrif
cd ../util/
jrifc -d /Users/wenyuanma/research/infoflow/JRIFsystem/jrif/lib-classes/ *.jrif
cd ../../..

