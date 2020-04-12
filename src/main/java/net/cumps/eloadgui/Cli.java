/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.cumps.eloadgui;

/**
 *
 * @author jancu
 * credit where credit due: http://www.thinkplexx.com/blog/simple-apache-commons-cli-example-java-command-line-arguments-parsing
 * 
 */

import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class Cli {
 private static final Logger log = Logger.getLogger(Cli.class.getName());
 private String[] args = null;
 private Options options = new Options();

 public Cli(String[] args) {

  this.args = args;

  options.addOption("h", "help", false, "show help.");
  options.addOption("u", "usb", true, "USB character device to listen to (e.g., COM13, /dev/ttyACM0)");
  options.addOption("b", "baud", true, "USB speed (baud, typical 115200)");

 }

 public void parse(USBParams params) {
  CommandLineParser parser = new DefaultParser();

  CommandLine cmd = null;
  try {
   cmd = parser.parse(options, args);

   if (cmd.hasOption("h"))
    help();

   if (cmd.hasOption("u")) {
    log.log(Level.INFO, "Using cli argument -usb=" + cmd.getOptionValue("u"));
    params.setConnectionName(cmd.getOptionValue("u"));
    
   } else {
    log.log(Level.SEVERE, "Missing usb option");
    help();
   }

   if (cmd.hasOption("b")) {
    log.log(Level.INFO, "Using cli argument -baud=" + cmd.getOptionValue("b"));
    params.setBaud(Integer.parseInt(cmd.getOptionValue("b")));    
   } else {
    log.log(Level.SEVERE, "Missing baud option");
    help();
   }
  
  } catch (ParseException e) {
   log.log(Level.SEVERE, "Failed to parse comand line properties", e);
   help();
  }
 }

 private void help() {
  // This prints out some help
  HelpFormatter formater = new HelpFormatter();

  formater.printHelp("Main", options);
  System.exit(0);
 }
}
