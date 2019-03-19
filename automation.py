import argparse
import os
import shutil
import subprocess
import time

TARGET_DIR = "target"
SOURCES_DIR = "target"
RESOURCES_DIR = "resources"

ACTION_CLEAR = "clear"
ACTION_COPY = "copy"
ACTION_LAUNCH = "launch"

TARGET_ALL = "all"
TARGET_LIB = "lib"
TARGET_RES = "res"
TARGET_JAR = "jar"

CFG_MAIN = "main"
CFG_TEST = "test"


class Artifact:
    def __init__(self, artifact_name, instance_name, launch_delay=0):
        self.__artifact_name = artifact_name
        self.__instance_name = instance_name
        self.__version = "-0.2.0"
        self.__launch_delay = launch_delay
        self.config = CFG_TEST

    def __get_target_dir(self):
        return TARGET_DIR + "\\" + self.__instance_name + "\\"

    def __get_target_lib_dir(self):
        return self.__get_target_dir() + "lib\\"

    def __get_sources_dir(self):
        return self.__artifact_name + "\\" + SOURCES_DIR + "\\"

    def __get_sources_lib_dir(self):
        return self.__get_sources_dir() + "lib\\"

    def __get_resources_dir(self):
        return RESOURCES_DIR + "\\" + self.config + "\\" + self.__instance_name + "\\"

    def __get_jar_name(self):
        return self.__artifact_name + self.__version + ".jar"

    def __get_core_jar_name(self):
        return "core" + self.__version + ".jar"

    def __get_configuration_file_name(self):
        return "configuration.xml"

    def create_target_dir(self):
        os.makedirs(self.__get_target_dir(), exist_ok=True)
        os.makedirs(self.__get_target_lib_dir(), exist_ok=True)

    def copy_jar(self):
        self.create_target_dir()
        shutil.copy2(self.__get_sources_dir() + self.__get_jar_name(), self.__get_target_dir())
        shutil.copy2(self.__get_sources_lib_dir() + self.__get_core_jar_name(), self.__get_target_lib_dir())

    def copy_lib(self):
        self.create_target_dir()
        shutil.rmtree(self.__get_target_lib_dir())
        shutil.copytree(self.__get_sources_lib_dir(), self.__get_target_lib_dir())

    def copy_resources(self):
        self.create_target_dir()
        shutil.copy2(self.__get_resources_dir() + self.__get_configuration_file_name(), self.__get_target_dir())

    def launch(self):
        launch_dir = os.getcwd() + "\\" + self.__get_target_dir()
        subprocess.Popen("java -jar " + launch_dir + self.__get_jar_name(), shell=True, close_fds=True, cwd=launch_dir)
        time.sleep(self.__launch_delay)


class Automation:
    def __init__(self):
        self.__artifacts = [
            Artifact("factory-container", "factory-container", launch_delay=2),
            Artifact("lego-container", "lego-container-1"),
            Artifact("lego-container", "lego-container-2"),
            Artifact("remote-container", "remote-container"),
        ]

    def set_config(self, config):
        for artifact in self.__artifacts:
            artifact.config = config

    def __clear_all(self):
        print("deleting previous deployment ...")
        if os.path.exists(TARGET_DIR):
            shutil.rmtree(TARGET_DIR)

    def clear(self, target):
        self.__clear_all()

    def __copy_jar_files(self):
        print("copying jar files ...")
        for artifact in self.__artifacts:
            artifact.copy_jar()

    def __copy_lib_files(self):
        print("copying lib files ...")
        for artifact in self.__artifacts:
            artifact.copy_lib()

    def __copy_resource_files(self):
        print("copying resource files ...")
        for artifact in self.__artifacts:
            artifact.copy_resources()

    def __copy_all(self):
        self.__clear_all()
        self.__copy_jar_files()
        self.__copy_lib_files()
        self.__copy_resource_files()

    def copy(self, target):
        action = {
            TARGET_ALL: self.__copy_all,
            TARGET_JAR: self.__copy_jar_files,
            TARGET_RES: self.__copy_resource_files,
            TARGET_LIB: self.__copy_lib_files,
        }
        action[target]()

    def launch(self, target):
        print("launching ...")
        for artifact in self.__artifacts:
            artifact.launch()


class Application:
    def __init__(self):
        self.__automation = Automation()

        self.__actions = {
            ACTION_CLEAR: self.__automation.clear,
            ACTION_COPY: self.__automation.copy,
            ACTION_LAUNCH: self.__automation.launch
        }

        self.__targets = {
            TARGET_ALL,
            TARGET_RES,
            TARGET_JAR,
            TARGET_LIB
        }

        self.__configs = {
            CFG_TEST,
            CFG_MAIN
        }

    def run(self):
        parser = argparse.ArgumentParser(description="automation deploy script")
        parser.add_argument("action", help="action to perform", choices=self.__actions)
        parser.add_argument("-t", "--target", help="part of software to deploy", choices=self.__targets, default=TARGET_ALL)
        parser.add_argument("-c", "--config", help="configuration file to use", choices=self.__configs, default=CFG_TEST)
        args = parser.parse_args()
        print("args=", args)

        self.__automation.set_config(args.config)
        self.__actions[args.action](args.target)
        print("done")


if __name__ == '__main__':
    Application().run()
