import argparse
import os
import shutil
import subprocess
import time


deploy_dir_base = "target"
resources_dir_base = "resources"
version = "-0.0.1-SNAPSHOT"


class Artifact:
    def __init__(self, artifact_name, instance_name):
        self.deploy_dir = deploy_dir_base + "\\" + instance_name
        self.sources_dir = artifact_name + "\\" + deploy_dir_base
        self.resources_dir = resources_dir_base + "\\" + instance_name
        self.jar_name = artifact_name + version + ".jar"

    def create_deploy_dir(self):
        os.makedirs(self.deploy_dir, exist_ok=True)
        os.makedirs(self.deploy_dir + "\\lib\\", exist_ok=True)

    def copy_jar(self):
        self.create_deploy_dir()
        core_jar_location = "\\lib\\" + "core" + version + ".jar"
        shutil.copy2(self.sources_dir + "\\" + self.jar_name, self.deploy_dir)
        shutil.copy2(self.sources_dir + core_jar_location, self.deploy_dir + core_jar_location)

    def copy_lib(self):
        self.create_deploy_dir()
        shutil.rmtree(self.deploy_dir+"\\lib")
        shutil.copytree(self.sources_dir + "\\lib", self.deploy_dir + "\\lib")

    def copy_configuration(self):
        self.create_deploy_dir()
        shutil.copy2(self.resources_dir + "\\configuration.xml", self.deploy_dir)

    def launch(self, base_dir):
        os.chdir(base_dir + "\\" + self.deploy_dir)
        subprocess.Popen("java -jar " + self.jar_name, shell=True, close_fds=True)


class Automation:
    def __init__(self):
        self.artifacts = [
            Artifact("factory-container", "factory-container"),
            Artifact("lego-container", "lego-container-1"),
            Artifact("lego-container", "lego-container-2"),
            Artifact("remote-container", "remote-container"),
        ]

    def clear(self):
        print("deleting previous deployment ...")
        if os.path.exists(deploy_dir_base):
            shutil.rmtree(deploy_dir_base)

    def create_folders(self):
        print("creating deployment structure ...")
        os.mkdir(deploy_dir_base)
        for artifact in self.artifacts:
            artifact.create_deploy_dir()

    def copy_jar_files(self):
        print("copying jar files ...")
        for artifact in self.artifacts:
            artifact.copy_jar()

    def copy_lib_files(self):
        print("copying lib files ...")
        for artifact in self.artifacts:
            artifact.copy_lib()

    def copy_configuration_files(self):
        print("copying configuration files ...")
        for artifact in self.artifacts:
            artifact.copy_configuration()

    def launch_jar_files(self):
        print("launching ...")
        current_dir = os.getcwd()
        for artifact in self.artifacts:
            artifact.launch(current_dir)
            time.sleep(2)


class ApplicationAction:
    def __init__(self, action_name, action_callback):
        self.action_name = action_name
        self.action_callback = action_callback

    def execute(self):
        self.action_callback()
        print("done")


class Application:
    def __init__(self):
        self.automation = Automation()
        self.application_actions = [
            ApplicationAction("copy-all", self.action_copy_all),
            ApplicationAction("copy-configuration", self.action_copy_configuration),
            ApplicationAction("copy-jar", self.action_copy_jar),
            ApplicationAction("copy-lib", self.action_copy_lib),
            ApplicationAction("clear-all", self.action_clear_all),
            ApplicationAction("launch-all", self.action_launch_all)
        ]

    def run(self):
        choices = []
        for application_action in self.application_actions:
            choices.append(application_action.action_name)

        parser = argparse.ArgumentParser()
        parser.add_argument("action", help="specify action to perform", choices=choices)

        args = parser.parse_args()

        is_application_action_found = False
        for application_action in self.application_actions:
            if args.action == application_action.action_name:
                application_action.action_callback()
                is_application_action_found = True
                break

        if not is_application_action_found:
            parser.print_usage()

    def action_copy_all(self):
        self.automation.clear()
        self.automation.copy_jar_files()
        self.automation.copy_lib_files()
        self.automation.copy_configuration_files()
        print("done")

    def action_clear_all(self):
        self.automation.clear()
        print("done")

    def action_copy_configuration(self):
        self.automation.copy_configuration_files()
        print("done")

    def action_copy_jar(self):
        self.automation.copy_jar_files()
        print("done")

    def action_copy_lib(self):
        self.automation.copy_lib_files()
        print("done")

    def action_launch_all(self):
        self.automation.launch_jar_files()
        print("done")


if __name__ == '__main__':
    Application().run()
