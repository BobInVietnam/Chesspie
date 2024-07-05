package com.mygdx.game.skills;

public enum SkillEffect {
        AttackSkill('A'), BuffSkill('U'), DefendSkill('D');

        private char value;

        SkillEffect(char value) {this.value = value;}

        public char getValue() {
                return value;
        }

        public void setValue(char value) {
                this.value = value;
        }
}
