#!/bin/sh
####################################
#
# Clean up classes
#
####################################

# Run it as  ./{name}.sh 

echo "Cleaning-up classes. "

rm sig-classes/java/io/*
rm sig-classes/java/lang/*
rm sig-classes/java/math/*
rm sig-classes/java/net/*
rm sig-classes/java/sql/*
rm sig-classes/jif/lang/*
rm sig-classes/jif/net/*
rm sig-classes/jif/runtime/*

rm lib-classes/jif/lang/*
rm lib-classes/jif/util/*

rm tests/reclassify/*.class
rm tests/reclassify/*.java

rm tests/jif/principals/*.class
rm tests/jif/principals/*.java

rm examples/rif_battleship/*.class
rm examples/rif_battleship/*.java

rm examples/rif_calendar/*.class
rm examples/rif_calendar/*.java
