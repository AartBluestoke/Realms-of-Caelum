/*
 * RealmsOfRainfur.cpp
 *
 *  Created on: Jun 12, 2012
 *      Author: Jeff "Elusivehawk" Brusse
 *
 */

#include "pandaFramework.h";
#include "pandaSystem.h";

PandaFramework framework;

int main(int argc, char *argv[]) {
  framework.open_framework(argc, argv);
  framework.set_window_title("Codename FSG Prototype");
  WindowFramework *window = framework.open_window();
  window->enable_keyboard();
  window->setup_trackball();

  if (window != (WindowFramework *)0)
  {
    nout << "Opened the window successfully!\n";

    // Put here your own code, such as the loading of your models

    framework.main_loop();
  }
  else
  {
    nout << "Could not load the window!\n";
  }
  framework.close_framework();
  return 0;
}
